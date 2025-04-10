package org.dows.log;

import lombok.RequiredArgsConstructor;
import org.dows.framework.api.i18n.UnifiedMessageSource;
import org.dows.framework.api.web.ResponseWrapperHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

@RequiredArgsConstructor
//
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer  {

    @Bean
    public UnifiedMessageSource unifiedMessageSource() {
        return new UnifiedMessageSource();
    }

    @Bean
    public ResponseWrapperHandler responseWrapperHandler() {
        return new ResponseWrapperHandler();
    }

//    @Bean
//
//    public ResourceHandlerRegistry addResourceHandlers( ) {
//        ResourceHandlerRegistry registry = new ResourceHandlerRegistry();
//        registry.addResourceHandler("/swagger-ui/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
//                .resourceChain(false);
//
//        registry.addResourceHandler("/favicon.ico")//favicon.ico
//                .addResourceLocations("classpath:/static/");
//
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//
//        registry.addResourceHandler("doc.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/doc.html#/**")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        // 配置访问前缀
//        registry.addResourceHandler("/templates/**")
//                //配置文件真实路径
//                .addResourceLocations("classpath:/templates/");
//
//    }


//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/swagger-ui/")
//                .setViewName("forward:/swagger-ui/index.html");
//    }


    /**
     * 增加头参数解析
     *
     * @param
     */
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(new HeaderArgumentResolver());
//    }
//
//
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        registry.addInterceptor(new SecurityInterceptor());
    }


    /**
     * 跨域处理
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //1,允许任何来源
        corsConfiguration.addAllowedOriginPattern(CorsConfiguration.ALL);
        //2,允许任何请求头
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        //3,允许任何方法
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        //4,允许凭证
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }


}