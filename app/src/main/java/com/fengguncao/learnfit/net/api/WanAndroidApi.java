package com.fengguncao.learnfit.net.api;

import com.fengguncao.learnfit.net.bean.ProjectBean;

import com.fengguncao.learnfit.net.bean.ProjectItem;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * name: WanAndroidApi<p>
 * description <p>
 *
 * @author YCKJ0165 <P>
 * date: 5/27/2020 <p>
 * copy: 重庆中科云从科技有限公司 <p>
 */
public interface WanAndroidApi {

    /**
     * 总数据
     *
     */
    @GET("project/tree/json")
    Observable<ProjectBean> getProject();  //异步线程，耗时操作


    /**
     * item数据  cid=294
     */
    @GET("project/list/{pageIndex}/json")
    Observable<ProjectItem> getProjectItem(@Path("pageIndex") int pageIndex, @Query("cid") int cid);
}
