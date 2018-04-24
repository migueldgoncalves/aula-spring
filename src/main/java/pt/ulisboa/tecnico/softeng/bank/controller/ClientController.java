package pt.ulisboa.tecnico.softeng.bank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.ulisboa.tecnico.softeng.bank.domain.Bank;

import pt.ulisboa.tecnico.softeng.bank.domain.Client;
import pt.ulisboa.tecnico.softeng.bank.dto.ClientDto;
import pt.ulisboa.tecnico.softeng.bank.exception.BankException;

import java.util.Set;


@Controller
@RequestMapping(value = "/banks/bank/{code}/clients")
public class ClientController {

    private static Logger logger = LoggerFactory.getLogger(ClientController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String bankForm(Model model, @PathVariable String code) {
        logger.info("clientForm");
        model.addAttribute("client", new ClientDto());
        model.addAttribute("clients", Bank.getBankByCode(code).getClients());
        return "clientView";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String clientForm(Model model, @PathVariable String code, @PathVariable String id) {
        logger.info("clientForm");

        Set<Client> clients = Bank.getBankByCode(code).getClients();
        Client client = Bank.getBankByCode(code).getClientById(id);
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setBankId(Bank.getBankByCode(code).getCode());
        clientDto.setName(client.getName());
        clientDto.setAge(client.getAge());


        model.addAttribute("client", clientDto);
        model.addAttribute("clients", clients);
        return "clientView";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String bankSubmit(Model model, @ModelAttribute ClientDto clientDto, @PathVariable String code) {
        logger.info("clientSubmit name:{}, code:{}", clientDto.getName(), clientDto.getId());

        try {

            new Client(Bank.getBankByCode(code),
                    Bank.getBankByCode(code).getCode() + Integer.toString((int) (Math.random() * 50 + 1)),
                    clientDto.getName(),
                    clientDto.getAge());

        } catch (BankException be) {
            model.addAttribute("error", "Error: it was not possible to create the bank");
            model.addAttribute("client", clientDto);
            model.addAttribute("clients", Bank.getBankByCode(clientDto.getBankId()).getClients());
            return "clientsView";
        }

        return "redirect:/banks/bank/" + Bank.getBankByCode(code).getCode() + "/clients";
    }

}
