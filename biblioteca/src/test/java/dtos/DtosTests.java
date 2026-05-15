package dtos;

import org.junit.jupiter.api.Test;

import com.fatesg.biblioteca.dtos.*;

public class DtosTests {
    @Test
    public void CargoDto_Deve_Existir(){
        assert CargoDto.class != null; 
    }

    @Test
    public void DepartamentoDto_Deve_Existir(){
        assert DepartamentoDto.class != null; 
    }

    @Test
    public void FolhaDto_Deve_Existir(){
        assert FolhaDto.class != null; 
    }
    
    @Test
    public void FuncionarioDto_Deve_Existir(){
        assert FuncionarioDto.class != null; 
    }

    @Test
    public void ReciboDto_Deve_Existir(){
        assert ReciboDto.class != null; 
    }

    @Test
    public void SalarioDto_Deve_Existir(){
        assert SalarioDto.class != null; 
    }
}
