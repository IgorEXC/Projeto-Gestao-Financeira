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

/*
*                     ------------Ajustes-------------
* 1 - Implementar despesas atrasadas, despesas pagas, despesas futuras. Planos e projetos
* ordem de compras pagas ou pendentes, investimentos, gastos essencias, gastos futeis.
* 2 - Fazer excel e relatorio jasper
* 3 - Tratar melhor as excecoes, qnd acontece uma excecao por causa do cpf ele nao avisa
* 4 - Ver por que data_atualizacao da auditoria nao esta funcionando
* 5 - Data da resposta get do expenses nao esta retornando nada
* 6 - Variaveis monetarias passarem para BigDecimal
* */


/*                   -----------Implementações-----------
* 1 - A categoria nao pode ter o limite de gastos, por que isso dependera
* do quanto cada usuario ganha. Por mes, usuario podera escolher ate 30% do seu
* salario para gastar com categorias nao necessarias, ou seja, as parcelas
* dos gastos do mes nao podem exceder 30% do seu salario com despesas nao necessarias.
* Ele tambem podera mudar isso, mas a criterio dele e no maximo ate 60% do salario.
*
* 2 - Um usuario nao podera comprar algo que nao seja necessario se for maior
* que 10 vezes o seu salario. Se for algo necessario como casa ou carro (se ja
* nao tiver um - tratar isso), o usuario podera comprar ate 100 vezes o seu salario.
*
* 3 - Havera despesas que serao rotineiras, como agua, luz e alimentacao por exemplo.
* Fazer logica que reserva, com base no salario do usuario e com a media de das ultimas
* 3 contas ou mais, o valor que tera que ser reservado para quitar essas dividas
*
* 4 - Implementar situacao do usuario, se ele esta devendo ou seja, seus gastos
* excederam seu salario. Se ele esta tranquilo, ou seja, sobrou pelo menos 30% do seu
* salario ao fim do mes depois de ter gasto com todas as despesas essenciais. Se esta
* apertado, ou seja, sobrou apenas 15% ou menos do seu salario. Se ficou sem dinheiro,
* sobrou nada do seu salario.
*
* 5 - Implementar servico que mostra por data (nao podendo passar da data atual), as
* despesas do usuario e fazer relacao com o que foi necessario e com o que nao foi.
* Entregar estatiscas para o usuario.
*
* 6 - Uma categoria nao pode estar associada ao usuario, mas um usuario pode estar
* associado a varias categorias. E nessa relacao de usuario para categorias que
* sera definida o teto maximo estipulado pelo usuario para a categoria. Lembrando que
* sera um teto maximo mensal, ou seja, nao pode passar dos 30%. Se o usuario precisar
* comprar um celular e ele custa 2000 e dividir de 10 vezes, seu salario tem que ser
* no minimo 1500 reais. Se o usuario consegue pagar a vista, ele pode pagar, ai entra
* a proxima funcionalidade.
*
* 7 - Implementar servico onde o usuario pode guardar dinheiro. Nele, ele podera
* comprar o que quiser a vista, isso se o dinheiro guardado der. Se o usuario estiver
* no vermelho/apertado, sugira usar o dinheiro para quitar as dividas ou se ele
* ainda nao conseguiu comprar algo necessario, sugira que compre.
*
* 8 - No relatorio, sera filtrado por data, e nele mostrara todos os gastos daquele
* usuario, bem como as categorias e os totais dos valores gastos e do que sobrou.
* E tambem mostrara se houve saldo negativo para aquele mes. Filtre por data mas cada
* mes sera um subrelatorio.
*
* 9 - Caso o usuario tenha cartao de credito, pedir para informar o limite. Caso tenha
* utilizado o cartao de credito, como nas despesas, a fatura do cartao se tornara um
* gasto necessario.
*
* * */