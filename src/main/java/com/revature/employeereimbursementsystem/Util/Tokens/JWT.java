package com.revature.employeereimbursementsystem.Util.Tokens;

import com.revature.employeereimbursementsystem.Model.Employee;
import com.revature.employeereimbursementsystem.Util.Exceptions.InvalidTokenException;
import com.revature.employeereimbursementsystem.Util.Exceptions.UnauthorizedException;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import javax.crypto.spec.SecretKeySpec;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;


public class JWT {

    private static Properties properties = new Properties();
    private static byte[] lazySaltBytes;

    static {
        try {
            properties.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lazySaltBytes = Base64.getEncoder().encode(
                Base64.getEncoder().encode(properties.getProperty("jwt-secret").getBytes())
        );
    }

    public String createToken(Employee employee) throws IOException {

        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(String.valueOf(employee.getEmployeeUsername()))
                .setSubject(employee.getEmployeeUsername())
                .setIssuer("ERS-jmatute")
                .claim("rank", employee.getRank())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000 * 8))
                .signWith(new SecretKeySpec(lazySaltBytes, "HmacSHA256"));

        return tokenBuilder.compact();
    }
    private Optional<Employee> parseToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(lazySaltBytes)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Optional.of(new Employee(String.valueOf(claims.getId()), claims.getSubject(),claims.get("rank").toString()));
    }

    public boolean isTokenValid(String token) {
        if(token == null || token.trim().equals("")) return false;
        return parseToken(token).isPresent();
    }

    public Employee extractTokenDetails(String token) {
        if(!isTokenValid(token)) {
            throw new UnauthorizedException("No established token.");
        }
        return parseToken(token).orElseThrow(InvalidTokenException::new);
    }
}
