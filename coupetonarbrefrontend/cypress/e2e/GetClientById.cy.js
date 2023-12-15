describe('Accounts Admin Page', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000/accounts');
  });

  it('should display client details when a client name is clicked', () => {
    const clientFirstName = 'Alick'; 
    const clientLastName = "Ucceli"
    const clientEmail = "aucceli0@dot.gov"
    const clientPhoneNumber = "514-837-9347"
    const clientAddress = "73 Shoshone Road, Barraute, Québec, Canada"

    cy.contains(clientFirstName).click();

    cy.get('.additional-details').should('be.visible');

    cy.contains(`Name: ${clientFirstName} ${clientLastName}`).should('be.visible');
    cy.contains(`Email: ${clientEmail}`).should('be.visible');
    cy.contains(`Phone Number: ${clientPhoneNumber}`).should('be.visible');
    cy.contains(`Address: ${clientAddress}`).should('be.visible');

    cy.get('button').contains('Close').click();
    cy.get('.additional-details').should('not.exist');
  });
});
