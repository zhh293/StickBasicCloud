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

@Activate(group = CommonConstants.PROVIDER)
public class UserContextProviderFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String userId = RpcContext.getServerAttachment().getAttachment("x-user-id");
        if (userId != null && !userId.isBlank()) {
            try {
                BaseContext.set(Long.parseLong(userId));
            } catch (NumberFormatException ignored) {
            }
        }
        try {
            return invoker.invoke(invocation);
        } finally {
            BaseContext.remove();
        }
    }
}
