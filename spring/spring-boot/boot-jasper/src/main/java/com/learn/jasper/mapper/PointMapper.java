/**
 * @author G-bug 2019/9/30
 */
package com.learn.jasper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.jasper.entity.Point;
import com.learn.jasper.entity.User;

import java.util.List;

public interface PointMapper extends BaseMapper<Point> {

    List<Point> selectLineCell();


    List<Point> selectMapCell();

}
