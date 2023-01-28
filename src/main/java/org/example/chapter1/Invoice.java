package org.example.chapter1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private String customer;
    private List<Performance> performances;

}
