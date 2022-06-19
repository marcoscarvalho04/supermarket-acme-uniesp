package br.com.customer.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerRequest implements Pageable {

    private String nome;
    private String email;
    private String cpf;

    @Override
    public int getNumberOfPages() {
        return 0;
    }

    @Override
    public PageFormat getPageFormat(int pageIndex) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public Printable getPrintable(int pageIndex) throws IndexOutOfBoundsException {
        return null;
    }
}
