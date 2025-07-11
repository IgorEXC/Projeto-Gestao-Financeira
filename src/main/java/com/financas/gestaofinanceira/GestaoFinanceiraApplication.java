package com.financas.gestaofinanceira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestaoFinanceiraApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoFinanceiraApplication.class, args);
	}

}

/*Implementar despesas atrasadas, despesas pagas, despesas futuras. Planos e projetos
* ordem de compras pagas ou pendentes, investimentos, gastos essencias, gastos futeis
* fazer excel e relatorio jasper*/