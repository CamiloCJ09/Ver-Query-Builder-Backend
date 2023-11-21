# QUERY BUILDER (VER+) 🎁
## Visual Query Builder 📈

Welcome to the Visual Query Builder – a user-friendly interface for crafting queries without the need to write SQL! 🚀

### Build Queries with Ease 👷

As a user, you can construct queries effortlessly using checkboxes, drop-down menus, and textboxes. The graphical elements adapt to the dataset, providing an intuitive experience. For example, on Mercado Libre, use sliders and checkboxes to filter by price ranges, item conditions, and shipping speed. 🛒

Explore the possibilities of UI filters inspired by Mercado Libre and Google's boolean logic for advanced users! 🔍

### Streamlined Interface ⚡

To enhance the user experience, the Visual Query Builder doesn't overwhelm you with every field or table. Choose a single use case and limit yourself to six fields or fewer for optimal usability. The tool seamlessly performs joins across multiple tables without burdening you with implementation details. 💭

### Run Queries on Your Terms 💻

Simply press the "Run Query" button to fetch data based on your graphical input. The query executes only when you decide to run it. ▶️

### Visual Summary of Queried Data 📉

After running a query, enjoy a creative and visually appealing representation of your data. Choose from line graphs, bar graphs, pie charts, or any visualization that suits your exploration needs. Make it pretty! 📊

### Save and Share Queries 💾

Save your queries with a name, comment, and your username. No need for login credentials – specify your username while using the app. Retrieve, share, and collaborate on saved queries with ease. 🔖

### Manage Saved Queries 📑

View a list of all saved queries with details like name, comment, and the creator's username. Collaborate with others by commenting on their queries. 💬

### Select Saved Queries 🔖

Effortlessly select a saved query from the list, and it will be loaded into the Visual Query Builder. Pick up where you left off with ease. 🔁

### System Administration ⚙️

#### Data Persistence 🔌

System administrators can safely shut down and reboot the application without losing data. Your system can withstand power outages with no loss of data. 💾

#### Multiplayer Functionality 👥

For system owners, support two or more users with separate usernames exploring data simultaneously. Enable collaborative data exploration with multiplayer functionality. 👥👥

Feel free to explore, create, and collaborate with the Visual Query Builder! 📊

## How it works 🎨
### First step 📢

You will be able to see different selectors with which you will be able to choose between:
- Country
- Year
- Economic indicator
- Minimum percentage value of that indicator

![First step](/images/image.png)

Within the countries you will be able to select the one you prefer, however be careful, as not all countries have all indicators applied

![Alt text](/images/image2.png)

Within the indicators you will be able to choose from a variety of global economic indicators to research. 

![Alt text](/images/image3.png)

This filter helps us to define from which year we want to start measuring, for example, I want to see indicator X but only from Y year.

![Alt text](/images/image4.png)

Within the value, we can select values from 0 to 100, which represent percentages. This will be useful to filter years from values higher than such percentage, for example: I want to filter the years in which the indicator X was higher than Y percentage. 

![Alt text](/images/image5.png)

Once all the values are filled in, it is necessary to press the Let's query button and the result will be as follows

A graph will be generated showing the behavior of the indicator over time.

![Alt text](/images/image6.png)

Hovering the mouse over the graph will show more detailed information.

![Alt text](/images/image7.png)

In addition, a button is enabled with which we can save the query we have just executed.

In this form we will enter our user name, the name of the query and a comment if necessary, it is not necessary to create the user, just enter the value that is yours and it will directly save the query in your account.

![Alt text](/images/image8.png)
![Alt text](/images/image10.png)

If we go to the queries tab we will be able to see a list of all the queries that have been created in the application so far along with all the information that we provided

![Alt text](/images/image11.png)

![Alt text](/images/image12.png)

If we press the see results button we will be able to see a preview of the executed query, if we scroll to the end we will see the following comment button.

![Alt text](/images/image13.png)

If we press this button we will see the following´

![Alt text](/images/image14.png)

Here we can comment on other users' queries, all we have to do is enter our user name and the comment we want to make.

![Alt text](/images/image15.png)

There you have it, your commentary, and the best part, they are anonymous!!!!

![Alt text](/images/image13.png)

If we use the edit query button it will take us to the following screen

![Alt text](/images/image16.png)

With this we will be able to edit the query that we have just seen in case we want to modify any of its parameters.

And this would be the whole guide of how to use the application.

## For developers 💻

A brief description of your project.

### Getting Started

Follow these steps to get your project up and running.

#### Prerequisites

Make sure you have Docker and Docker Compose installed on your machine.

#### Installation

1. Clone the repository:

    ```bash
    git clone <repository_url>
    cd <repository_directory>
    ```

2. Build and run Docker Compose:

    ```bash
    docker-compose up -d
    ```

    This command will build and start the Docker containers in the background.

3. Access the application in the browser:

    Open your favorite web browser and go to [http://localhost:your_port](http://localhost:your_port).

    Replace `your_port` with the port specified in your `docker-compose.yml` file.

    You should see your application up and running! Happy testing! 🚀

### Contributing

If you'd like to contribute to this project, please follow the [Contributing Guidelines](CONTRIBUTING.md).

### License

This project is licensed under the [License Name](LICENSE) - see the [LICENSE](LICENSE) file for details.