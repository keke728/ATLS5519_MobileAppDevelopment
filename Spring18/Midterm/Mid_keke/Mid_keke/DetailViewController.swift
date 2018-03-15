//
//  DetailViewController.swift
//  Mid_keke
//
//  Created by Keke Wu on 3/15/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit

class DetailViewController: UITableViewController {
    
    var rests = [String]()
    var selectedRes = 0
    var resListDetail = Restaurants()
    var searchController : UISearchController!
    
    @IBAction override func unwind(for unwindSegue: UIStoryboardSegue, towardsViewController subsequentVC: UIViewController) {
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        resListDetail.res = Array(resListDetail.resData.keys)
        let chosenRes = resListDetail.res[selectedRes]
        rests = resListDetail.resData[chosenRes]! as [String]
        let resultsController = SearchViewController() //create an instance of our SearchResultsController class
        resultsController.allRes = rests //set the allwords property to our words array
        searchController = UISearchController(searchResultsController: resultsController) //initialize our search controller with the resultsController when it has search results to display
    
        //search bar configuration
        searchController.searchBar.placeholder = "Enter a search term" //place holder text
        searchController.searchBar.sizeToFit() //sets appropriate size for the search bar
        tableView.tableHeaderView=searchController.searchBar //install the search bar as the table header
        searchController.searchResultsUpdater = resultsController
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        print("view will disappear")
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return rests.count
    }



}
