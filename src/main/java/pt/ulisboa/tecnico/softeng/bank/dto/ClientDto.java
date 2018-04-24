package pt.ulisboa.tecnico.softeng.bank.dto;

public class ClientDto {
	String name;
	String id;
	int age;
	String bankId;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String code) {
		this.id = code;
	}

	public int getAge() { return this.age; }

	public void setAge(int age) {
	    this.age = age;
    }
}
