package com.tedu.sp11.service.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.tedu.web.util.JsonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderServiceFallback implements FallbackProvider{
	
	@Override
	public String getRoute() {
		// TODO Auto-generated method stub
		return "order-service";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		// TODO Auto-generated method stub
		return response();
	}

	private ClientHttpResponse response() {
		// TODO Auto-generated method stub
		return new ClientHttpResponse() {

			@Override
			public InputStream getBody() throws IOException {
				log.info("fallback body");
				String s = JsonResult.err().msg("后台服务错误").toString();
				
				return new ByteArrayInputStream(s.getBytes("utf-8"));
			}

			@Override
			public HttpHeaders getHeaders() {
				// TODO Auto-generated method stub
				HttpHeaders hearders = new HttpHeaders();
				
				hearders.setContentType(MediaType.APPLICATION_JSON);
				return hearders;
			}

			   @Override
	            public HttpStatus getStatusCode() throws IOException {
	                return HttpStatus.OK;
	            }
	            @Override
	            public int getRawStatusCode() throws IOException {
	                return HttpStatus.OK.value();
	            }
	            @Override
	            public String getStatusText() throws IOException {
	                return HttpStatus.OK.getReasonPhrase();
	            }

	            @Override
	            public void close() {
	            }

			
		};
	}

	
	
}
