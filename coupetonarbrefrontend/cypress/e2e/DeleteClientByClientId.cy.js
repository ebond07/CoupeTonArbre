describe('FirstTest', () => {

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




    cy.get('nav').contains('Login').click();

    cy.origin('https://dev-4hcoszrtvq0f1jnk.us.auth0.com', () => {
    cy.get('#username').type('evanbond007@outlook.com'); 
    cy.get('#password').type('EvanPassword123'); 
    
    cy.get('button:contains("Continue")').first().click({force : true});
  })
 


  cy.get('nav').contains('Accounts').click();
});
  it('opens the page and performs e2e testing', () => {
    // Visit the home page
    cy.visit('localhost:3000');

    // Verify that the Navbar is rendered
    cy.get('#nav-container').should('exist');
    cy.get('nav').should('exist');

    // Verify the presence of welcome message and content
    cy.contains('Welcome to the Home Page').should('exist');
    cy.contains('This is the content of the home page.').should('exist');

    // Verify the Navbar links
    cy.get('nav').contains('Home').should('exist');
    cy.get('nav').contains('Appointments').should('exist');
    cy.get('nav').contains('Quote Requests').should('exist');
    cy.get('nav').contains('Accounts').should('exist');
    cy.get('nav').contains('Feedback').should('exist');
    cy.get('nav').contains('Logout').should('exist');


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

    // Delete the first five clients
    for (let i = 0; i < 5; i++) {
      // Verify the presence of client information
      cy.get('tbody').find('tr').should('have.length.gt', 0); 

      // Interact with the delete button and confirm
      cy.get('tbody tr:first-child').as('firstClient');
      cy.intercept('DELETE', '**/users/clients/*').as('deleteRequest'); // Intercept DELETE requests

      cy.get('@firstClient').find('button').contains('Delete').click();
      
      // Confirm the deletion
      cy.on('window:confirm', () => true);

      // Wait for the intercepted request and stub the response
      cy.wait('@deleteRequest').then((interception) => {
        expect(interception.response.statusCode).to.equal(204); // Assuming a successful deletion
      });

      

      
    }
  });
});
