package com.financas.GestaoFinanceira.test;


import com.financas.GestaoFinanceira.domain.*;
import com.financas.GestaoFinanceira.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
@Profile("test")
public class TestConfiguration implements CommandLineRunner {
	
	DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final UserRepository userRepository;

	private final CategoryRepository categoryRepository;

	private final ExpenseRepository expenseRepository;

	private final FinancialPlanningRepository financialPlanningRepository;

	private final ReportRepository reportRepository;

	private final UserExpenseRepository userExpenseRepository;
	
	@Override
	public void run(String... args) throws Exception {

		User  user1 = User.builder().name("Igor")
				.cpf("15684310664")
				.monthlyIncome(2100.00)
				.email("igor@gmail.com")
				.build();

		User  user2 = User.builder().name("Maria")
				.cpf("12345678911")
				.monthlyIncome(5500.00)
				.email("maria@gmail.com")
				.build();

		userRepository.saveAll(Arrays.asList(user1, user2));
		
//		user1.getTelephoneContacts().addAll(Arrays.asList("31999999954"));
//		user2.getTelephoneContacts().addAll(Arrays.asList("44898564212", "55988946712"));
//
//		userRepository.saveAll(Arrays.asList(user1, user2));
		
//		FinancialPlanning fp1 = new FinancialPlanning(null, 20000.00, 1500.00, user1);
//		FinancialPlanning fp2 = new FinancialPlanning(null, 35000.00, 3600.00, user2);
//		FinancialPlanning fp3 = new FinancialPlanning(null, 15000.00, 1250.00, user1);
//		financialPlanningRepository.saveAll(Arrays.asList(fp1, fp2, fp3));
//
//		user1.getFinancialPlanning().addAll(Arrays.asList(fp1, fp3));
//		user2.getFinancialPlanning().addAll(Arrays.asList(fp2));
//		userRepository.saveAll(Arrays.asList(user1, user2));
//
//		Expense ex1 = new Expense(null, "Iorgute", 13.99, LocalDate.parse("10/12/2024", fmt1), false, fp1);
//		Expense ex2 = new Expense(null, "RTX 2080", 1800.99, LocalDate.parse("19/10/2022", fmt1), false, fp3);
//		Expense ex3 = new Expense(null, "Arroz 5kg", 31.98, LocalDate.parse("28/11/2024", fmt1), true, fp2);
//		Expense ex4 = new Expense(null, "AMD Ryzen 7", 1500.99, LocalDate.parse("02/08/2023", fmt1), true, fp3);
//		expenseRepository.saveAll(Arrays.asList(ex1, ex2, ex3, ex4));
//
//		UserExpense ue1 = new UserExpense(ex1, user1, 4);
//		UserExpense ue2 = new UserExpense(ex2, user1, 1);
//		UserExpense ue3 = new UserExpense(ex3, user2, 2);
//		UserExpense ue4 = new UserExpense(ex4, user1, 1);
//
//		user1.getUserExpenses().addAll(Arrays.asList(ue1, ue2, ue4));
//		user2.getUserExpenses().addAll(Arrays.asList(ue3));
//
//		ex1.getUsers().addAll(Arrays.asList(ue1));
//		ex2.getUsers().addAll(Arrays.asList(ue2));
//		ex3.getUsers().addAll(Arrays.asList(ue3));
//		ex4.getUsers().addAll(Arrays.asList(ue4));
//		userExpenseRepository.saveAll(Arrays.asList(ue1, ue2, ue3, ue4));
//
//		Category cat1 = new Category(null, "Informática", 500.00);
//		Category cat2 = new Category(null, "Alimentação", 850.00);
//		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
//
//		ex1.getCategories().addAll(Arrays.asList(cat2));
//		ex2.getCategories().addAll(Arrays.asList(cat1));
//		ex3.getCategories().addAll(Arrays.asList(cat2));
//		ex3.getCategories().addAll(Arrays.asList(cat1));
//		expenseRepository.saveAll(Arrays.asList(ex1, ex2, ex3, ex4));
//
//		cat1.getExpenses().addAll(Arrays.asList(ex1, ex2, ex4));
//		cat2.getExpenses().addAll(Arrays.asList(ex3));
//		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		
		Report r1 = new Report(null, user1);
		Report r2 = new Report(null, user2);
		reportRepository.saveAll(Arrays.asList(r1, r2));
	}
}
