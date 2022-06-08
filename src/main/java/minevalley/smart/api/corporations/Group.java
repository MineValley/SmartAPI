package minevalley.smart.api.corporations;

import minevalley.smart.api.Registered;
import minevalley.smart.api.economy.BankAccount;

import java.util.List;

public interface Group extends Registered {

    /**
     * Gets the id of this group.
     *
     * @return id of group
     */
    int getId();

    /**
     * Gets the name of this group.
     *
     * @return name of group
     */
    String getName();

    /**
     * Gets this group's description
     *
     * @return description as string
     */
    String getDescription();

    /**
     * Gets whether this group is a company (otherwise: organization).
     *
     * @return true, if group is company
     */
    boolean isCompany();

    /**
     * Gets this group's owner.
     *
     * @return unique id of owner as string
     */
    String getOwner();

    /**
     * Gets a list of all operators.
     *
     * @return list of all operators' unique ids
     */
    List<String> getOperators();

    /**
     * Gets the department, which is markes as default.
     *
     * @return group's default department
     */
    Department getDefaultDepartment();

    /**
     * Gets a list of all departments.
     *
     * @return departments as list
     */
    List<Department> getDepartments();

    /**
     * Gets the bank account of this group.
     *
     * @return bank account of group
     */
    BankAccount getBankAccount();
}
