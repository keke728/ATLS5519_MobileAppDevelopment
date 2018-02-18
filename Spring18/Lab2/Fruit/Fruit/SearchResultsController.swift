//
//  SearchResultsController.swift
//  Fruit
//
//  Created by Keke Wu on 2/17/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit

class SearchResultsController: UITableViewController, UISearchResultsUpdating {
    
    var allfruits = [String]()
    var filteredfruits = [String]()
    
    func updateSearchResults(for searchController: UISearchController) {
        let searchString = searchController.searchBar.text //search string
        filteredfruits.removeAll(keepingCapacity: true) //removes all elements
        if searchString?.isEmpty == false {
            let searchfilter: (String) -> Bool = {name in
                let range = name.range(of: searchString!, options:.caseInsensitive)
                return range != nil
            }
            let matches = allfruits.filter(searchfilter)
            filteredfruits.append(contentsOf: matches)
        }
        tableView.reloadData()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.backgroundView = UIImageView(image: UIImage(named:"bg2")!)
        tableView.separatorStyle = UITableViewCellSeparatorStyle.none
        //register our table cell identifier
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "CellIdentifier")
        

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }
    
    //

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return filteredfruits.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "CellIdentifier", for: indexPath)
        // Configure the cell...
        cell.textLabel?.text = filteredfruits[indexPath.row]
        cell.textLabel?.font = UIFont(name:"Kefa", size:18)
        return cell
    }
    

    //Make TableView Cell transparent
    override func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        cell.backgroundColor = .clear
    }
}
