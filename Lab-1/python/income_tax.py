# function to computer income tax based on assignment formular using the rates and chargeable income
def compute_tax(income: float) -> float:
    """
        Calculate net income based on the provided formula with different tax rates for different income ranges.

        Parameters:
        - income (float): The total income for which the tax is to be calculated.

        Returns:
        - float: The calculated net income.

        Example:
        >>> compute_tax(2000)
        1743.85
    """
    # Initialize variables
    taxable_amount: float = income
    tax_payable: float = 0

    # Check income against different tax brackets and calculate tax payable
    # First 402: 0% on the first 402 and below
    if taxable_amount >= 402:
        taxable_amount -= 402

        # Next 110: 5% on the first 110
        if taxable_amount >= 110:
            tax_payable += 0.05 * 110
            taxable_amount -= 110

            # Next 130: 10% on the next 130
            if taxable_amount >= 130:
                tax_payable += 0.1 * 130
                taxable_amount -= 130

                # Third 130: 17.5% on the next 3000
                if taxable_amount >= 3000:
                    tax_payable += 0.175 * 3000
                    taxable_amount -= 3000

                    # Fourth bracket: 25% on the next 16395
                    if taxable_amount >= 16395:
                        tax_payable += 0.25 * 16395
                        taxable_amount -= 16395

                        # Fifth bracket: 30% on the next 29963
                        if taxable_amount >= 29963:
                            tax_payable += 0.3 * 29963
                            taxable_amount -= 29963

                            # Sixth bracket: 35% on the remaining amount if more than 50000
                            if taxable_amount >= 50000:
                                tax_payable += 0.35 * 50000
                                taxable_amount -= 50000
                            elif taxable_amount != 0:
                                # If remaining amount is less than 50000, apply 35% on the remaining amount
                                tax_payable += 0.35 * taxable_amount

                        elif taxable_amount != 0:
                            # If remaining amount is less than 29963, apply 30% on the remaining amount
                            tax_payable += 0.3 * taxable_amount

                    elif taxable_amount != 0:
                        # If remaining amount is less than 16395, apply 25% on the remaining amount
                        tax_payable += 0.25 * taxable_amount
                elif taxable_amount != 0:
                    # If remaining amount is less than 3000, apply 17.5% on the remaining amount
                    tax_payable += 0.175 * taxable_amount
            elif taxable_amount != 0:
                # If remaining amount is less than 130, apply 10% on the remaining amount
                tax_payable += 0.1 * taxable_amount
        elif taxable_amount != 0:
            # If remaining amount is less than 110, apply 5% on the remaining amount
            tax_payable += 0.05 * taxable_amount

    return income - tax_payable


print(compute_tax(100000))
