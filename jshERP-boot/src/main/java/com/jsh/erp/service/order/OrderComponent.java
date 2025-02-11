package com.jsh.erp.service.order;

import com.alibaba.fastjson.JSONObject;
import com.jsh.erp.service.ICommonQuery;
import com.jsh.erp.utils.Constants;
import com.jsh.erp.utils.StringUtil;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service(value = "order_component")
@OrderResource
public class OrderComponent  implements ICommonQuery {

    @Resource
    private OrderService orderService;

    @Override
    public Object selectOne(final Long id) throws Exception {
        return null;
    }

    @Override
    public List<?> select(final Map<String, String> map) throws Exception {
        String search = map.get(Constants.SEARCH);
        String designer = StringUtil.getInfo(search, "designer");
        String beginTime = StringUtil.getInfo(search, "beginTime");
        String endTime = StringUtil.getInfo(search, "endTime");
        Long organId = StringUtil.parseStrLong(StringUtil.getInfo(search, "organId"));
        Long status = StringUtil.parseStrLong(StringUtil.getInfo(search, "status"));
        List<?>  result = orderService.select(beginTime,endTime,designer,organId,status);
        return result;
    }

    @Override
    public Long counts(final Map<String, String> parameterMap) throws Exception {
        return null;
    }

    @Override
    public int insert(final JSONObject obj, final HttpServletRequest request) throws Exception {
        return 0;
    }

    @Override
    public int update(final JSONObject obj, final HttpServletRequest request) throws Exception {
        return 0;
    }

    @Override
    public int delete(final Long id, final HttpServletRequest request) throws Exception {
        return 0;
    }

    @Override
    public int deleteBatch(final String ids, final HttpServletRequest request) throws Exception {
        return 0;
    }

    @Override
    public int checkIsNameExist(final Long id, final String name) throws Exception {
        return 0;
    }
}
