## Will rewrite the whole project to use Spring as backend RESTFUL API
## Will rewrite the whole app on a containerized environment
## Most likely use Kotlin for the backend
## Will use Angular for the frontend


# Stocks Tracker

This is a stock tracking application built with **Java Spring Boot** for the backend and **Vaadin** for the frontend. The application connects to **MongoDB Atlas** for database management. Users can search for stocks, add them to a watchlist, and view detailed information about each stock.

## Features

- **Search for Stocks:** Users can search for stocks by entering a stock symbol or company name.
- **Watchlist Management:** Users can add stocks to a watchlist and view a table of saved stocks with key market data.
- **Stock Details Panel:** A detailed view showing the current price, market high, market low, previous close, and last updated time for selected stocks.
- **News Panel:** Displays relevant news articles for the selected stock (if implemented).

## Technologies Used

- **Backend:** Java Spring Boot, MongoDB
- **Frontend:** Vaadin
- **Database:** MongoDB Atlas
- **Version Control:** Git, GitHub

## Installation

### Prerequisites

- Java 17 or later
- MongoDB Atlas account
- RapidAPI account (if using a stock data API)

### Setup

1. Clone the repository:

   \`\`\`bash
   git clone https://github.com/dotsz/stocks_tracker.git
   cd stocks_tracker
   \`\`\`

2. Create a \`credentials.properties\` file in the \`src/main/resources\` directory with the following content:

   \`\`\`properties
   MONGODB_URI=<Your MongoDB Atlas URI>
   RAPIDAPI_KEY=<Your RapidAPI Key>
   \`\`\`

3. Build and run the application:

   \`\`\`bash
   ./mvnw spring-boot:run
   \`\`\`

   This will start both the backend and frontend since Vaadin is integrated with Spring Boot.

## Usage

1. Open your web browser and go to [stocks.victorfelipejr.com](http://stocks.victorfelipejr.com).
2. **Search for Stocks:** Enter a stock symbol or company name in the search bar and click the **Search** button.
3. **View Stock Details:** The stock's details, such as current price, market high, market low, previous close, and last updated time, will be displayed in the details panel.
4. **Add to Watchlist:** Click the **Add to Watchlist** button to save the selected stock to your watchlist.
5. **Save Watchlist:** You can save your current watchlist by clicking the **Save Watchlist** button. This will store your selected stocks in the database.
6. **View Watchlist:** Your watchlist, displaying key information like stock name, symbol, current price, market high, market low, previous close, and last updated time, is displayed below the search and details panels.

## Deployment

1. Push your code to GitHub and connect your repository to your preferred cloud platform (e.g., Render, Heroku).
2. Ensure your environment variables (\`MONGODB_URI\`, \`RAPIDAPI_KEY\`) are set correctly in your cloud platform dashboard.
3. Deploy the application.


## Acknowledgments

- Special thanks to the developers of Spring Boot, Vaadin, and MongoDB for their excellent frameworks and tools.

---

# Stocks Tracker - User Guide

Welcome to the Stocks Tracker! This guide will help you navigate and use the application to track your favorite stocks easily.

## Getting Started

### Accessing the Application

1. Open your web browser (e.g., Chrome, Firefox, Safari).
2. Go to the Stocks Tracker website by entering the following URL: [stocks.victorfelipejr.com](http://stocks.victorfelipejr.com).

### Searching for Stocks

1. On the homepage, you'll find a search bar at the top left.
2. Type in the stock symbol (like "AAPL" for Apple) or the company name you want to search for.
3. Click the **Search** button.
4. The app will display detailed information about the stock in the panel on the left side of the page, including the current price, market high, market low, previous close, and last updated time along with relevant stock news on the news panel.

### Adding Stocks to Your Watchlist

1. After searching for a stock, you’ll see an **Add to Watchlist** button near the search bar.
2. Click on **Add to Watchlist** to save this stock to your watchlist.
3. Your watchlist will appear below the search and details panels, showing all the stocks you've added.

### Saving Your Watchlist

1. Once you have added the desired stocks to your watchlist, click the **Save Watchlist** button.
2. This will save your watchlist to the database, allowing you to view it later.

### Viewing Your Watchlist

1. Scroll down to see your watchlist.
2. The watchlist displays all the stocks you’ve added, along with key information like the current price, market high, market low, previous close, and last updated time.

## Troubleshooting

### I Can’t Find a Stock

- Make sure you are entering the correct stock symbol/ticker.
- If the stock is not found, it might not be listed in the available data sources.

### My Watchlist Isn’t Updating

- Try refreshing the page in your web browser.
- Ensure that your internet connection is stable.

### Something Isn’t Working Right

- If you encounter any issues, try restarting the application by closing the browser and reopening the link.
- If the problem persists, you may need to contact support.


** To be implemented: **
- User Authentication
- User-based Watchlists