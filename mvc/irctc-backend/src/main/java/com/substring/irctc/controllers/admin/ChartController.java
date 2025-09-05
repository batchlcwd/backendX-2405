package com.substring.irctc.controllers.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin/charts")
public class ChartController {

    @GetMapping("/sales-week")
    public List<SalesPoint> salesWeek() {
        // Normally you’d fetch from DB/service; here’s static demo data (last 7 days)
        LocalDate today = LocalDate.now();

        return List.of(
                new SalesPoint(today.minusDays(6), 1200),
                new SalesPoint(today.minusDays(5), 900),
                new SalesPoint(today.minusDays(4), 1450),
                new SalesPoint(today.minusDays(3), 800),
                new SalesPoint(today.minusDays(2), 1300),
                new SalesPoint(today.minusDays(1), 1600),
                new SalesPoint(today,           1100)
        );
    }

    @GetMapping("/sales-by-category")
    public List<CategorySales> salesByCategory() {
        return List.of(
                new CategorySales("Electronics", 4200),
                new CategorySales("Fashion",     3100),
                new CategorySales("Home",        2100),
                new CategorySales("Grocery",     1700)
        );
    }



}

record SalesPoint(LocalDate date, double amount) {

}

 record CategorySales(String category, double amount) {}



/*

@Query("""
    select CategorySales(s.category, SUM(s.amount))
    from Sale s
   where (:from is null or s.date >= :from)
      and (:to   is null or s.date <= :to)
    group by s.category
    order by SUM(s.amount) desc
  """)


 */

