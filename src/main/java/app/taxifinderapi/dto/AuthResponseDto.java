package app.taxifinderapi.dto;

import org.springframework.security.oauth2.core.OAuth2AccessToken;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponseDto {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("access_token_expiry")
    private int access_token_expiry;

    @JsonProperty("token_type")
    private OAuth2AccessToken.TokenType tokenType;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_id")
    private Long user_id;

    private AuthResponseDto(AuthResponseDtoBuilder builder) {
        this.accessToken = builder.accessToken;
        this.access_token_expiry = builder.access_token_expiry;
        this.tokenType = builder.tokenType;
        this.userName = builder.userName;
        this.user_id = builder.user_id;
    }

    public static class AuthResponseDtoBuilder {

        private String accessToken;
        private int access_token_expiry;
        private OAuth2AccessToken.TokenType tokenType;
        private String userName;
        private Long user_id;

        public AuthResponseDtoBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public AuthResponseDtoBuilder accessTokenExpiry(int access_token_expiry) {
            this.access_token_expiry = access_token_expiry;
            return this;
        }

        public AuthResponseDtoBuilder tokenType(OAuth2AccessToken.TokenType tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public AuthResponseDtoBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public AuthResponseDtoBuilder userId(Long user_id) {
            this.user_id = user_id;
            return this;
        }

        public AuthResponseDto build() {
            return new AuthResponseDto(this);
        }
    }

}
