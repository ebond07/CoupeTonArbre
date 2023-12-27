describe('AddClientByAdminTest', () => {
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
 


  cy.get('nav').contains('Logout').should('exist');

  cy.get('nav').contains('Accounts').click();
});
  it('allows an admin to add a new client', () => {
    // Visit the AccountsAdmin page

    // Check for the presence of the "Add Client" button and click it
    cy.contains('Add Client').should('exist').click();

    // Fill out the form fields with client information
    cy.get('input[name="firstName"]').type('Da Zhuo');
    cy.get('input[name="lastName"]').type('Xie');
    cy.get('input[name="email"]').type('dazhuoxie1024@gmail.com');
    cy.get('input[name="phoneNumber"]').type('(514) 550-6578');
    cy.get('input[name="address"]').type('1175 rue Rembrandt, Brossard, Québec, Canada');

    // Submit the form
    cy.contains('Submit New Client').click();

    // Verify that the new client appears in the client list
    cy.contains('Da Zhuo').should('exist');
    cy.contains('Xie').should('exist');
    cy.contains('dazhuoxie1024@gmail.com').should('exist');
    cy.contains('(514) 550-6578').should('exist');
    cy.contains('1175 rue Rembrandt, Brossard, Québec, Canada').should('exist');

    // Optionally, verify that the form is no longer visible
    cy.contains('Submit New Client').should('not.exist');
  });
});
