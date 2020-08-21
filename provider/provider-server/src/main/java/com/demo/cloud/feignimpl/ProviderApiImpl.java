package com.demo.cloud.feignimpl;

import com.demo.cloud.domain.Product;
import com.demo.cloud.providerapi.feign.ProviderApi;
import com.demo.cloud.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: lzc
 * @Description:
 * @Date: Create in 15:53 2020/7/23
 */
@RestController("productApi")
@RequestMapping(value = "/product")
public class ProviderApiImpl implements ProviderApi {
    @Autowired
    private IProviderService IProviderService;

    @Override
    public List<Product> queryListById(Long id) {
        //System.out.println("当前线程的名称:" + Thread.currentThread().getName());
        //ExecutorService service = Executors.newSingleThreadExecutor();
        //List<Product> list = null;
        //try {
        //    Future<List<Product>> submit = service.submit(() -> IProviderService.queryListById(id));
        //    list = submit.get();
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //} catch (ExecutionException e) {
        //    e.printStackTrace();
        //}finally {
        //    service.shutdown();
        //}
        List<Product> list = IProviderService.queryListById(id);
        return list;
    }
}
