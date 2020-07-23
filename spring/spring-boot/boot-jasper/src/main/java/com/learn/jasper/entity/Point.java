package com.learn.jasper.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author g-bug
 * @date 2020/7/24 上午6:50
 */
@Data
public class Point extends Model<Point> {

    private int x;

    private int y;

    private String type;

    private String cont;

    private String cellCode;

    List<Point> row;

    public Point(int x, int y, String cellCode, String type, String cont) {
        this.x = x;
        this.y = y;
        this.cellCode = cellCode;
        this.type = type;
        this.cont = cont;
    }

    public Point() {

    }

    public static List<Point> getTest() {
        return new ArrayList<Point>() {{
            add(new Point() {{
                // 第一行对象
                this.setX(1);
                this.setY(-1);
                // 行数据
                this.setRow(new ArrayList<Point>() {{
                    // 第一个
                    add(new Point() {{
                        this.setX(1);
                        this.setY(1);
                        this.setType("2");
                        this.setCont("银行资产");
                    }});
                    add(new Point() {{
                        this.setX(1);
                        this.setY(2);
                        this.setType("1");
                        this.setCont("34.567");
                    }});
                }});
            }});
            add(new Point() {{
                // 第一行对象
                this.setX(2);
                this.setY(-1);
                // 行数据
                this.setRow(new ArrayList<Point>() {{
                    // 第一个
                    add(new Point() {{
                        this.setY(1);
                        this.setType("2");
                        this.setCont("表内资产");
                    }});
                    add(new Point() {{
                        this.setY(2);
                        this.setType("3");
                    }});
                }});
            }});
            add(new Point() {{
                // 第一行对象
                this.setX(3);
                this.setY(-1);
                // 行数据
                this.setRow(new ArrayList<Point>() {{
                    // 第一个
                    add(new Point() {{
                        this.setY(1);
                        this.setType("2");
                        this.setCont("表外资产");
                    }});
                    add(new Point() {{
                        this.setY(2);
                        this.setType("1");
                        this.setCont("9993");
                    }});
                }});
            }});
            add(new Point() {{
                // 第一行对象
                this.setX(1);
                this.setY(-1);
                // 行数据
                this.setRow(new ArrayList<Point>() {{
                    // 第一个
                    add(new Point() {{
                        this.setY(1);
                        this.setType("2");
                        this.setCont("全部资产");
                    }});
                    add(new Point() {{
                        this.setY(2);
                        this.setType("1");
                        this.setCont("002.4");
                    }});
                }});
            }});
        }};
    }

    public static void main(String[] args) {

        List<Point> points = new ArrayList<Point>() {{
            add(new Point(1, 1, "A1", "5", "B4+A3"));
            add(new Point(1, 2, "A2", "5", "B3+B4"));
            add(new Point(1, 3, "A3", "1", "11"));
            add(new Point(2, 1, "B1", "5", "B2+B3"));
            add(new Point(2, 1, "B2", "1", "13"));
            add(new Point(2, 1, "B3", "1", "14"));
            add(new Point(2, 1, "B4", "5", "B1+B2"));
        }};

        System.out.println();

        Point point = points.stream().filter(o -> "A1".equals(o.getCellCode())).findAny().orElse(null);
        points.forEach(o -> {
            compiler(points, o);
        });

        List<Point> list = toDesigns(points);

        System.out.println();
    }

    public static List<Point> toDesigns(List<Point> source) {
        Map<Integer, List<Point>> rowMap = source.stream().collect(
                Collectors.groupingBy(Point::getX, Collectors.toList())
        );

        List<Point> list = new ArrayList<>();
        for (Map.Entry<Integer, List<Point>> entry : rowMap.entrySet()) {
            list.add(new Point() {{
                setX(entry.getKey());
                setRow(entry.getValue());
            }});
        }
        return list;
    }

    public static Point compiler(List<Point> points, Point point) {
        String[] childPointCode = point.getCont().split("\\+");
        Integer[] sum = new Integer[childPointCode.length];
        int i = 0;

        for (String chile : childPointCode) {
            Point dependPoint = points.stream().filter(o -> chile.equals(o.getCellCode())).findAny().orElse(null);
            if ("5".equals(point.getType())) {
                sum[i] = Integer.valueOf(compiler(points, dependPoint).getCont());
                dependPoint.setType("1");
                dependPoint.setCont(sum[i].toString());
                i++;
            } else {
                sum[i++] = Integer.valueOf(point.getCont());
            }
        }

        point.setCont((Arrays.stream(sum).mapToInt(o -> o).sum()) + "");
        point.setType("1");
        return point;
    }
}
