describe('Accounts Admin Page', () => {
  beforeEach(() => {
    
    cy.visit('localhost:3000');

    // Verify that the Navbar is rendered
    cy.get('#nav-container').should('exist');
    cy.get('nav').should('exist');

    // Verify the presence of welcome message and content
    cy.contains('Welcome to the Home Page').should('exist');
    cy.contains('This is the content of the home page.').should('exist');

    // Verify the Navbar links
    cy.get('nav').contains('Home').should('exist');
    // cy.get('nav').contains('Appointments').should('exist');
    // cy.get('nav').contains('Quote Requests').should('exist');
    // cy.get('nav').contains('Accounts').should('exist');
    // cy.get('nav').contains('Feedback').should('exist');
    cy.get('nav').contains('Login').should('exist');





   cy.adminLogin()
 

  cy.get('nav').contains('Logout').should('exist');

  cy.get('nav').contains('Accounts').click();
});


  it('should display client details when a client name is clicked', () => {
    const clientFirstName = 'Alick'; 
    const clientLastName = "Ucceli"
    const clientEmail = "aucceli0@dot.gov"
    const clientPhoneNumber = "(514) 837-9347"
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
