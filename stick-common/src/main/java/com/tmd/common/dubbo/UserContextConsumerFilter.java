package com.tmd.common.dubbo;

import com.tmd.common.util.BaseContext;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;

@Activate(group = CommonConstants.CONSUMER)
public class UserContextConsumerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Long userId = BaseContext.get();
        if (userId != null) {
            RpcContext.getClientAttachment().setAttachment("x-user-id", String.valueOf(userId));
        }
        return invoker.invoke(invocation);
    }
}
