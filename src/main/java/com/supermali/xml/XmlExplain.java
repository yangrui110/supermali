package com.supermali.xml;

import com.supermali.util.FileUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析xml文件
 * */
public class XmlExplain {

    private SAXReader saxReader;
    private List<EntityDom> result;

    public XmlExplain() {
        this.saxReader = new SAXReader();
        result=new ArrayList<>();
    }

    public void explain(String path){
//        String path ="/map.xml/Fisrt.xml";
        InputStream asStream = this.getClass().getResourceAsStream(path);
        try {
            byte[] bytes = FileUtil.readFileToByte(asStream);
            String s = new String(bytes);
            s=s.trim();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(s.getBytes());
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            for(Element element: elements){
                String entity = element.attributeValue("entity");
                boolean existEntity = checkExistEntity(entity);
                if(!existEntity){
                    throw new RuntimeException("该类:"+entity+"不存在，请重新配置");
                }
                List<EntityDom> entityDoms = parseElement(element);
                result.addAll(entityDoms);
            }
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

    }

    /**
     * 解析一个元素
     * 一个实体元素。比如: <sky>...</sky>
     * */
    private List<EntityDom> parseElement(Element element){
        ArrayList<EntityDom> list = new ArrayList<>();
        String entity = element.attributeValue("entity");
        List<Element> elements = element.elements();
        for(Element one: elements){
            String name = one.getName();
            if("listfactor".equalsIgnoreCase(name)){
                EntityDom dom = new EntityDom();
                String x = one.attributeValue("x");
                String y = one.attributeValue("y");
                String cx = one.attributeValue("cx");
                String cy = one.attributeValue("cy");
                String order = one.attributeValue("order");
                String shape = one.attributeValue("shape");
                int x1 = Integer.parseInt(x);
                int y1 = Integer.parseInt(y);
                int cx1 = Integer.parseInt(cx==null?"1":cx);
                int cy1 = Integer.parseInt(cy==null?"1":cy);
                int order1 = Integer.parseInt(order == null ? "0" : order);
                dom.setX(x1);
                dom.setY(y1);
                dom.setCx(cx1);
                dom.setCy(cy1);
                dom.setShape(shape);
                dom.setOrder(order1);
                dom.setEntityPath(entity);
                list.add(dom);
            }
        }
        return list;
    }

    /**
     * 判断是否存在该实体
     * */
    private boolean checkExistEntity(String entityPath){
        try{
            Class.forName(entityPath);
        }catch (ClassNotFoundException e){
            return false;
        }
        return true;
    }

    public List<EntityDom> getResult() {
        return result;
    }
}
