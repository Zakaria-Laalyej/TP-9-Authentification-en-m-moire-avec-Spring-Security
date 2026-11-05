package ma.fstg.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
        System.out.println("==================================================================");
        System.out.println(" TP 9 : Spring Security en memoire demarre avec succes !");
        System.out.println(" URL : http://localhost:8080/");
        System.out.println(" Comptes de test :");
        System.out.println("   - Utilisateur : user / 1111 (Role: USER)");
        System.out.println("   - Administrateur : admin / 1234 (Roles: ADMIN, USER)");
        System.out.println("==================================================================");
    }
}
