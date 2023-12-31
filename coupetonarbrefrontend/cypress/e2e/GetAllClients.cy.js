describe('FirstTest', () => {
  it('opens the page and performs e2e testing', () => {
    // Visit the home page
    cy.visit('localhost:3000');

    // Verify that the Navbar is rendered
    cy.get('#nav-container').should('exist');
    cy.get('nav').should('exist');

    // Verify the presence of welcome message and content
    cy.contains('4 Steps Until The Job Is Done').should('exist');

    // Verify the Navbar links
    cy.get('nav').contains('Home').should('exist');
    // cy.get('nav').contains('Appointments').should('exist');
    // cy.get('nav').contains('Quote Requests').should('exist');
    // cy.get('nav').contains('Accounts').should('exist');
    // cy.get('nav').contains('Feedback').should('exist');
    cy.get('nav').contains('Login').should('exist');





    cy.adminLogin();


    // Interact with the Navbar 
    cy.get('nav').contains('Accounts').click();

    // Validate the URL
    cy.url().should('include', '/admin/accounts');

    // Verify the presence of the table and its headers
    cy.get('table').should('exist');
    cy.get('th').contains('First Name').should('exist');
    cy.get('th').contains('Last Name').should('exist');
    cy.get('th').contains('Email').should('exist');
    cy.get('th').contains('Phone Number').should('exist');
    cy.get('th').contains('Address').should('exist');

    // Verify the presence of client information
    cy.get('tbody').find('tr').should('have.length', 10); 

    cy.get('nav').contains('Logout').should('exist');


    cy.get('nav').contains('Appointments').click();

    cy.url().should('include', '/admin/appointments');

    cy.contains('Welcome to the Appointments Page').should('exist');
    cy.contains('This is the content of the appointments page.').should('exist');

    cy.get('nav').contains('Quote Requests').click();
    cy.url().should('include', '/admin/quotes');

    cy.contains('Welcome to the Quotes Page').should('exist');
    cy.contains('This is the content of the quote page.').should('exist');

    cy.get('nav').contains('Feedback').click();
    cy.url().should('include', '/admin/feedback');

    cy.contains('Welcome to the Feedback Page').should('exist');
    cy.contains('This is the content of the feedback page.').should('exist');
  });
});
