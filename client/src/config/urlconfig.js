var authApiUrl = 'http://localhost:8081';
var authApiRegisterUrl = `${authApiUrl}/register`;
var authApiTokenUrl = `${authApiUrl}/oauth/token?grant_type=password`;
var authApiRefreshUrl = `${authApiUrl}/oauth/token?grant_type=refresh_token`;

var resourceApiUrl = 'http://localhost:8080';
var resourceApiItems = `${resourceApiUrl}/items`;

export { authApiRegisterUrl, authApiTokenUrl, authApiUrl, authApiRefreshUrl} ;