//
//  SearchViewController.swift
//  Mid_keke
//
//  Created by Keke Wu on 3/15/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit

class SearchViewController: UITableViewController, UISearchResultsUpdating {


    var allRes = [String]()
    var filteredRes = [String]()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "cell")
        // Do any additional setup after loading the view.
    }
    
    func updateSearchResults(for searchController: UISearchController) {
        let searchString = searchController.searchBar.text //search string
        filteredRes.removeAll(keepingCapacity: true) //removes all elements
        if searchString?.isEmpty == false {
            //closure that will be called for each word to see if it matches the search string
            let filter: (String) -> Bool = { name in
                //look for the search string as a substring of the word
                let range = name.range(of: searchString!, options: NSString.CompareOptions.caseInsensitive, range: nil, locale: nil)
                return range != nil //returns true if the value matches and false if there’s no match
            } //end closure
            let matches = allRes.filter(filter)
            filteredRes.append(contentsOf: matches)
        }
        tableView.reloadData() //reload table data with search results
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return filteredRes.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
        cell.textLabel?.text = filteredRes[indexPath.row]
        return cell
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
