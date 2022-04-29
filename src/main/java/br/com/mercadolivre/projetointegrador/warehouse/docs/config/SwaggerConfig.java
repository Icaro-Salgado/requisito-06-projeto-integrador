package br.com.mercadolivre.projetointegrador.warehouse.docs.config;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI meliFreshOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("MercadoFresh - Projeto Integrador")
                        .description("MercadoFresh API - Projeto Integrador Bootcamp Mercado Libre - Grupo(Javali) - Wave 5")
                        .version("v1.0.0")
                        .license(new License().name("Mercado Livre").url("https:www.mercadolibre.com"))
                        .contact(
                                new Contact()
                                        .name("Javali Developers")
                                        .url("https://github.com/Icaro-Salgado/projeto-integrador")
                                        .email("evandro.sutil@mercadolivre.com;" +
                                                "icaro.salgado@mercadolivre.com;" +
                                                "klinton.lee@mercadolivre.com;" +
                                                "maran.morimoto@mercadolivre.com;" +
                                                "paulo.alima@mercadolivre.com;" +
                                                "pedro.levada@mercadolivre.com;" +
                                                "thainan.santos@mercadolivre.com"))
                ).externalDocs(
                        new ExternalDocumentation()
                                .description("GitHub")
                                .url("https://github.com/Icaro-Salgado/projeto-integrador"));
    }
}