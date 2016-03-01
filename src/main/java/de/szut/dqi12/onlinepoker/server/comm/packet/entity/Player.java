
package de.szut.dqi12.onlinepoker.server.comm.packet.entity;

import de.szut.dqi12.onlinepoker.server.comm.packet.JSONSerializable;
import de.szut.dqi12.onlinepoker.server.comm.packet.Packet;
import org.json.JSONObject;

public class Player implements JSONSerializable {

    public Integer id;
    public Integer money;
    public String username;
    public String firstname;
    public String lastname;
    public String email;
    public String password;



    /**
     * Login constructor
     *
     * @param username
     * @param password
     */
    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Player(Integer id, Integer money, String username, String firstname, String lastname, String email, String password) {
        this.id = id;
        this.money = money;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toJSON() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", this.id);
        jsonObject.put("username", this.username);
        jsonObject.put("password", this.password);
        jsonObject.put("email", this.email);
        jsonObject.put("lastname", this.lastname);
        jsonObject.put("firstname", this.firstname);
        jsonObject.put("money", this.money);

        return jsonObject.toString();
    }
}
