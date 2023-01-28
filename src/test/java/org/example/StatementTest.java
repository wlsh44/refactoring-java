package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.chapter1.Invoice;
import org.example.chapter1.Statement;
import org.example.chapter1.PlayInfo;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StatementTest {

    Statement statement = new Statement();

    @Test
    void mainTest() throws Exception {
        //given
        String playsJson = """
                {
                  "hamlet": {
                    "name": "hamlet",
                    "type": "tragedy"
                  },
                  "as-like": {
                    "name": "As You Like It",
                    "type": "comedy"
                  },
                  "othello": {
                    "name": "Othello",
                    "type": "tragedy"
                  }
                }
                """;
        String invoiceJson = """
                [
                  {
                    "customer": "BigCo",
                    "performances": [
                      {
                        "playID": "hamlet",
                        "audience": 55
                      },
                      {
                        "playID": "as-like",
                        "audience": 35
                      },
                      {
                        "playID": "othello",
                        "audience": 40
                      }
                    ]
                  }
                ]
                """;
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, PlayInfo> plays = objectMapper.readValue(playsJson, new TypeReference<>() {});
        Invoice invoice = objectMapper.readValue(invoiceJson, Invoice.class);

        //when
        String result = statement.statement(invoice, plays);

        //then
        assertThat(result).isEqualTo("""
                청구 내역 (고객명: BigCo)
                   Hamlet: $650.00 (55석)
                   As You Like It: $580.00 (35석)
                   Othello: $500.00 (40석)
                총액: $1,730.00
                적립 포인트: 47점
                """);
    }
}
