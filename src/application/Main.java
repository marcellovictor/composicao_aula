package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Main {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat standard_date = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat month_year = new SimpleDateFormat("MM/yyyy");
		
		//Instanciação do departamento:
		System.out.print("Enter department's name: ");
		String dep = sc.nextLine();
		Department department = new Department(dep);
		
		//Worker data:
		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		String name = sc.nextLine();
		
		System.out.print("Level: ");
		String level = sc.nextLine();
		WorkerLevel wlevel = WorkerLevel.valueOf(level);
		
		System.out.print("Base salary: ");
		Double base_salary = sc.nextDouble();
		
		//Instanciação do Worker:
		
		Worker worker = new Worker(name, wlevel, base_salary, department);
		
		// Contratos:
		
		System.out.print("How many contracts to this worker? ");
		int n = sc.nextInt();
		
		for (int i = 0; i<n; i++) {
			System.out.printf("Enter contract #%d data:\n", (i+1));
			
			System.out.print("Date (DD/MM/YYYY): ");
			sc.nextLine();
			String date = sc.nextLine();
			Date con_date = standard_date.parse(date);
			
			System.out.print("Value per hour: ");
			Double vph = sc.nextDouble();
			
			System.out.print("Duration (hours): ");
			Integer duration = sc.nextInt();
			
			//Instanciação do contrato:
			
			HourContract contract = new HourContract(con_date, vph, duration);
			worker.addContract(contract);
		}
		
		//Calculando income:
		
		System.out.println();
		System.out.print("Enter month and year to calculate income (MM/YYYY): ");
		sc.nextLine();
		String month_analysed = sc.nextLine();
		Date month_contract = month_year.parse(month_analysed);
		
		Calendar cal_income = Calendar.getInstance();
		cal_income.setTime(month_contract);
		
		Double income = worker.income(cal_income.YEAR, (cal_income.MONTH + 1));
		
		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.print("Income for " + month_analysed + ": ");
		System.out.printf("%.2f\n", income);
		
		
		sc.close();

	}

}
