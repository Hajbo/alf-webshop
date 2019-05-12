var authApiUrl = 'http://localhost:8081';
var authApiRegisterUrl = `${authApiUrl}/register`;
var authApiTokenUrl = `${authApiUrl}/oauth/token?grant_type=password`;

export { authApiRegisterUrl, authApiTokenUrl, authApiUrl} ;