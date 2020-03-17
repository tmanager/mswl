package com.frank.mswl.mini.crontab;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 获取报告定时任务Repository.
 *
 * @author 张孝党 2020/01/10.
 * @version V1.00.
 * <p>
 * 更新履历： V1.00 2020/01/10 张孝党 创建.
 */
@Mapper
@Repository
public interface TaskRepository {

    /**
     * 取得国际版最新状态为02(已上传检测中的列表)
     */
    List<Map<String, String>> getCheckingReports();

    /**
     * 取得UK版最新状态为02(已上传检测中的列表)
     */
    List<Map<String, String>> getCheckingReportsUK();

    /**
     * 取得Grammarly最新状态为02(已上传检测中的列表)
     */
    List<Map<String, String>> getCheckingReportsGrammarly();

    /**
     * 更新订单状态.
     */
    int updOrderStatusById(Map<String, String> param);

    /**
     * 获取失败的订单任务.
     */
    List<Map<String, String>> getFailureOrderList();

    /**
     * 删除失败订单.
     */
    int delFailureOrder(Map<String, String> param);
}
