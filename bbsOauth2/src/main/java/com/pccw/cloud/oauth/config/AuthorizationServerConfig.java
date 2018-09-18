package com.pccw.cloud.oauth.config;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	@Value("${spring.cloud.oauth2.token.validity.secends}")
	private Integer AccessTokenValiditySeconds;
	
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Oauth2UserDetailsService oauth2UserDetailsService;
 
    @Autowired
    private DataSource dataSource;
    @Bean // 声明TokenStore实现
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
    
    @Autowired
    private RedisConnectionFactory connectionFactory;
    
    @Bean
    public TokenStore redisTokenStore() {
        RedisTokenStore redis = new RedisTokenStore(connectionFactory);
        return redis;
    }
    
    @Bean // 声明 ClientDetails实现
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }
    @Override // 配置框架应用上述实现
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	TokenStore tokenStore = redisTokenStore();
        endpoints.userDetailsService(oauth2UserDetailsService);
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenEnhancer(accessTokenConverter());
        tokenServices.setAccessTokenValiditySeconds(AccessTokenValiditySeconds);
        tokenServices.setClientDetailsService(clientDetails());
        tokenServices.setAuthenticationManager(authenticationManager);
        endpoints.tokenServices(tokenServices);
    }
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
    	JwtAccessTokenConverter converter = new JwtAccessTokenConverter(){
    		@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				String userName = authentication.getUserAuthentication().getName();
				final Map<String, Object> additionalInformation = new HashMap<String, Object>();
				additionalInformation.put("user_name", userName);
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
				OAuth2AccessToken token = super.enhance(accessToken, authentication);
				return token;
			} 
    	};
    	KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("ouyang_key.jks"), "oyh1203oy".toCharArray())
			.getKeyPair("ouyang_key");
    	converter.setKeyPair(keyPair);
    	return converter;
    }
    
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}
	
    @Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}
    
    /**
	 * 不重写该方法时获取token会提示401 Full authentication is required to access this resource
	 * */
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {  
//		//enable client to get the authenticated when using the /oauth/token to get a access token  
//		//there is a 401 authentication is required if it doesn't allow form authentication for clients when access /oauth/token
//		oauthServer.allowFormAuthenticationForClients();  
//	}

}
