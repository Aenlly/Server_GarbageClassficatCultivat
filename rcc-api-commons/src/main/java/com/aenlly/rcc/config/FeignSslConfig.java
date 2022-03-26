package com.aenlly.rcc.config;

import feign.Client;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author Aenlly||tnw
 * @create by date 2022/03/26 20:45
 */
@Configuration
public class FeignSslConfig {

  @Bean
  @ConditionalOnMissingBean
  public Client feignClient() {
    try {
      SSLContext ctx = SSLContext.getInstance("SSL");
      X509TrustManager tm =
          new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {}

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {}

            @Override
            public X509Certificate[] getAcceptedIssuers() {
              return null;
            }
          };
      ctx.init(null, new TrustManager[] {tm}, null);
      return new Client.Default(ctx.getSocketFactory(), (hostname, session) -> true);
    } catch (Exception e) {
      return null;
    }
  }
}
