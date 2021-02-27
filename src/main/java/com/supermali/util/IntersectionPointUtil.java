package com.supermali.util;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * @project super-mali
 * @Date 2021/2/27
 * @Auth yangrui
 **/
public class IntersectionPointUtil {

    public static final double EPSILON = 0.0000001;

    /**
     * 获取线线的交点
     * */
    public static Point2D lineLineIntersection(Line2D line1,Line2D line2){
        // 把线转为向量的表现形式
        Point2D A = line1.getP1();
        Point2D B = line1.getP2();
        Point2D C = line2.getP1();
        Point2D D = line2.getP2();
        Vector2f AsubB = Vector2f.getVector2f(B, A);
        Vector2f DsubC = Vector2f.getVector2f(C, D);
        //
        Vector2f DsubCperp = DsubC.perp();
        double f = DsubCperp.dot(AsubB);
        if(Math.abs(f)<EPSILON){
            return null;
        }
        Vector2f AsubC = Vector2f.getVector2f(C, A);
        double d = DsubCperp.dot(AsubC);
        if(f>0){
            if(d<0||d>f) return null;
        }else {
            if(d>0||d<f) return null;
        }
        Vector2f BsubA = Vector2f.getVector2f(A, B);
        Vector2f BsubAperp = BsubA.perp();
        double e = BsubAperp.dot(AsubC);
        if(f>0){
            if(e<0||e>f) return null;
        }else {
            if(e>0||e<f) return null;
        }
        Vector2f f1 = new Vector2f(A.getX(), A.getY());
        Vector2f f2 = f1.add(BsubA).mul(d / f);
        return new Point2D.Double(f2.getX(),f2.getY());
    }
    /**
     * 获取直线和矩形的交点
     * @param line2D 直线
     * @param rectangle 矩形
     * */
    public static List<Point2D> lineRectangleIntersection(Line2D line2D, Rectangle rectangle){
        // 计算矩形的四个点
        double x = rectangle.getX();
        double y = rectangle.getY();
        double height = rectangle.getHeight();
        double width = rectangle.getWidth();

        ArrayList<Point2D> list = new ArrayList<>();
        list.add(new Point2D.Double(x,y));
        list.add(new Point2D.Double(x+width,y));
        list.add(new Point2D.Double(x+width,y+height));
        list.add(new Point2D.Double(x,y+height));
        // 计算直线矩形相交的点
        List<Point2D> result = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            int end = i+1;
            if(i==list.size()-1){
                end = 0;
            }
            Line2D d = new Line2D.Double(list.get(i), list.get(end));
            Point2D intersection = lineLineIntersection(line2D, d);
            if(intersection !=null){
                result.add(intersection);
            }
        }
        return result;
    }

    /**
     * 获取直线和多边形的交点
     * @param line2D 直线
     * @param polygon 多边形
     * */
    public static List<Point2D> linePolygonIntersection(Line2D line2D, Polygon polygon){
        int[] xpoints = polygon.xpoints;
        int[] ypoints = polygon.ypoints;
        int npoints = polygon.npoints;
        // 计算直线矩形相交的点
        List<Point2D> result = new ArrayList<>();
        for(int i=0;i<npoints;i++){
            int end = i+1;
            if(i==npoints-1){
                end = 0;
            }
            Line2D d = new Line2D.Double(new Point2D.Double(xpoints[i],ypoints[i]), new Point2D.Double(xpoints[end],ypoints[end]));
            Point2D intersection = lineLineIntersection(line2D, d);
            if(intersection !=null){
                result.add(intersection);
            }
        }
        return result;
    }
    /**
     * 计算直线和圆的交点
     * @param line2D 直线
     * @param center 圆中心点
     * @param r 圆半径
     * */
    public static List<Point2D> lineCircleIntersection(Line2D line2D,Point2D center,double r){
        Point2D p1 = line2D.getP1();
        Point2D p0 = line2D.getP2();
        Vector2f d = Vector2f.getVector2f(p0,p1);
        d = d.norm();
        Vector2f V = Vector2f.getVector2f(center, p0);
        double b = d.dot(V);
        double bb = b * b;
        double rr = r * r;
        double vv = V.dot(V);
        double c = vv - rr;
        if(bb<c){
            return new ArrayList<>();
        }
        double root = Math.sqrt(bb - c);
        ArrayList<Point2D> list = new ArrayList<>();

        Point2D point2D = calcPoint(-b-root, p0, d);
        Point2D point2D1 = calcPoint(-b+root, p0, d);
        if(point2D.getX()==point2D1.getX()&&point2D.getY()==point2D1.getY()){
            list.add(point2D);
        }else {
            list.add(point2D);
            list.add(point2D1);
        }
        return list;
    }

    private static Point2D calcPoint(double t, Point2D p, Vector2f d){
        // 获取交点
        Vector2f result = new Vector2f(p.getX(), p.getY());
        Vector2f add = result.add(d.mul(t));
        return new Point2D.Double(add.getX(),add.getY());
    }
    /**
     * 获取点到线的距离
     * */
    public static double pointLineDistance(Point2D point2D,Line2D line2D){
        Point2D S = line2D.getP1();
        Point2D Q = line2D.getP2();
        Vector2f f = Vector2f.getVector2f(S, Q);
        Vector2f norm = f.perp().norm();
        Vector2f f1 = Vector2f.getVector2f(Q, point2D);
        return Math.abs(norm.dot(f1));
    }
}
