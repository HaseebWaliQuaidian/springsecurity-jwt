Spring Secruity using JWT Auth
Here are steps how to work on it
The process is defined in 2 chunks, 1 (getting the token after login). 2 If token is presented, validating the resuming the request chain.

Below are the steps for 1st chunk of the work
1 - First of all we need to skip the FilterChain (for authentication/login purpose)
2 - We need to add a controller so that we can validate userName and password (in this case AuthController)
3 - Inside authController we will use AuthenticationManager and once validated, we will create JWT Token
4 - Return this token to the client

Below are the steps for 2nd step, where we are validating if the token existing is valid or not
1 - Create a custom JWT filter (in this case JWTAuthFilter) extending OncePerRequestFilter(spring web filter)
2 - override the doFilter method and check if the filter is presented in the header has valid filter
3 - Check if  and check if it exsits in Spring SecurityContextHolder is empty (first request after login) If not follow 4 else 5
4 - Check if the token is Valid, add the token in Spring's SecurityContextHolder and resume the filterChain process
5 - If SecurityContextHolder already have userObject stored, just resume the filterChain process
