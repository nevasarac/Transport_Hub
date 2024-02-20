# Transportation Reservation System

## Project Description
A startup aims to develop a system where passengers can make reservations from various transportation companies. These transportation companies offer services via railway, road, and airway options. Some companies can provide services in multiple modes (e.g., airway and road).

## Features

### Admin Panel
- Login with username and password.
- Only one admin account is allowed.
- Actions available in the admin panel:
  - View existing companies.
  - Register a new company.
  - Delete a company.
  - Set service fee (fixed daily charge - $1000).

### Company Panel
- Login with username and password.
- Each company should have recorded username, password, and vehicle information.
- Actions available in the company panel:
  - Add or remove vehicles.
  - Add or remove trips.
  - Calculate daily profit. Daily profit calculation should consider passenger fares, service fee, personnel costs, and fuel costs.

### User Panel
- Direct login.
- No need for user registration.
- Accessible via a "Search for Tickets" button.
- Actions available in the user panel:
  - Listing transportation options for a specific date.
  - Making a reservation.
  - A user can make multiple reservations simultaneously.
  - Seats should be marked as filled once reserved, preventing double booking.
  - Selecting departure point, destination, and travel date before starting the reservation process.
  - The system will display only 1-week travel dates between December 4th and December 10th, 2023.
  - Users also need to specify the number of passengers.
  - After selecting preferences, clicking the "Find Trip" button will query suitable trips.
  - The system will show available vehicles and their seat statuses for the selected date.
  - Users must select the trip of their preferred company.
  - Displaying available and reserved seat information for the selected trip.
  - Users must choose empty seats based on the number of passengers.
  - A separate data entry screen will open for each passenger.
  - Users need to input passenger information such as name, surname, ID number, and date of birth.
  - Once all passenger information is entered, clicking the "Make Payment" button will proceed to the payment process.
  - Upon successful payment, the system will display the selected trip and reserved seats.

## Instructions for Running the Project
1. Clone the repository to your local machine.
2. Install the required dependencies.
3. Run the application.
4. Access the system via the provided URLs for the admin, company, and user panels.
5. Follow the user guide to navigate through the system and utilize its features.

 
