/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.util.Date;
import model.AtendimentoModel;
import controller.AtendimentoController;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Héctor França
 */
public class TestController {

    @Test
    public void saveTest() throws SQLException {
        AtendimentoModel atendimentoModel = new AtendimentoModel();
        AtendimentoController atendimentoController = new AtendimentoController();

        atendimentoModel.setNome("Cliente");
        atendimentoModel.setData(new Date());

        int codigo = atendimentoController.save(atendimentoModel);

        assertEquals(true, codigo != 0);
    }

    @Test
    public void getFirstTest() {
        AtendimentoController atendimentoController = new AtendimentoController();

        try {
            atendimentoController.getFirst();
        } catch (SQLException exception) {
            fail("Erro ao recuperar o registro.");
        }
    }

    @Test
    public void getNextListTest() {
        AtendimentoController atendimentoController = new AtendimentoController();

        try {
            atendimentoController.getNextList();
        } catch (SQLException exception) {
            fail("Erro ao recuperar os registros.");
        }

    }

    @Test
    public void getChamadosList() {
        AtendimentoController atendimentoController = new AtendimentoController();

        try {
            atendimentoController.getChamadosList();
        } catch (SQLException exception) {
            fail("Erro ao recuperar os registros.");
        }

    }

    @Test
    public void getChamadoTest() {
        AtendimentoController atendimentoController = new AtendimentoController();

        try {
            atendimentoController.getChamado();
        } catch (SQLException exception) {
            fail("Erro ao recuperar o registro.");
        }

    }

    @Test
    public void updateJaAtendidoTest() {
        AtendimentoController atendimentoController = new AtendimentoController();

        try {
            atendimentoController.updateJaAtendido();
        } catch (SQLException exception) {
            fail("Erro ao atualizar os registros.");
        }

    }

    @Test
    public void updateTest() {
        AtendimentoModel atendimentoModel = new AtendimentoModel();
        AtendimentoController atendimentoController = new AtendimentoController();

        atendimentoModel.setNome("Cliente");
        atendimentoModel.setData(new Date());

        try {
            atendimentoController.save(atendimentoModel);
            atendimentoModel.setNome("Cliente Atualizado");

            atendimentoController.update(atendimentoModel);
        } catch (SQLException exception) {
            fail("Erro ao atualizar o registro.");
        }

    }
}
