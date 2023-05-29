// package cn.xxl.platform.system.config;
//
// import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
// import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
// import org.apache.shiro.spring.web.config.ShiroWebConfiguration;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// /**
//  * @author xiaoxiaolong
//  * @since 2023/5/15
//  */
// @Configuration
// public class ShiroConfig extends ShiroWebConfiguration {
//
//     @Bean
//     public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//         DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//         chainDefinition.addPathDefinition("/**", "anon"); // all paths are managed via annotations
//
//         // or allow basic authentication, but NOT require it.
//         // chainDefinition.addPathDefinition("/**", "authcBasic[permissive]");
//         return chainDefinition;
//     }
// }
