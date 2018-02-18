//
//  ViewController.swift
//  Fruit
//
//  Created by Keke Wu on 2/17/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit

class ViewController: UITableViewController {
    
    let kfilename = "data.plist"
    var fruitlist = Fruits()
    var iconsArray: [String] = ["apple","apricot","avocado","banana","cherry","dragonfruit","grape","honeydew",
    "kiwi","peach","pear","pineapple","pomegranate","raspberry","strawberry","watermelon"];
    //Create an array for icon names
    
    var searchController: UISearchController! // An instance of UISearchController
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //Set BG image
        tableView.backgroundView = UIImageView(image: UIImage(named:"bg2")!)
        //Enable large titles
        navigationController?.navigationBar.prefersLargeTitles = true
        
        let pathURL:URL?
        // Get path for data file
        let dirpath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        let docDir = dirpath[0] //documents directory
        print(docDir)
        
        //URL for our plist
        let dataFileURL = docDir.appendingPathComponent(kfilename)
        print(dataFileURL)
        
        // If data file exists, use it
        if FileManager.default.fileExists(atPath: dataFileURL.path){
            pathURL = dataFileURL
        }
        else{
            //URL for our plist
            pathURL = Bundle.main.url(forResource:"Fruit", withExtension:"plist")
        }
        
        //Create a property list decoder object
        let plistdecoder = PropertyListDecoder()
        do {
            let data = try Data(contentsOf: pathURL!)
            //decodes the property list
            fruitlist.fruitData = try plistdecoder.decode([String:[String]].self, from: data)
            fruitlist.fruits = Array(fruitlist.fruitData.keys).sorted() // Sort Fruits by names
        }catch{
            //handle error
            print(error)
        }
        
        //Application instance
        let app = UIApplication.shared
        //subscribe to the UIApplicationWillResignActiveNotification notification
        NotificationCenter.default.addObserver(self, selector: #selector(ViewController.applicationWillResignActive(_:)), name: NSNotification.Name.UIApplicationWillResignActive, object: app)
        
        // Search results
        let resultsController = SearchResultsController() //An instance of SearchResultController
        resultsController.allfruits = fruitlist.fruits //Set the allfruits to fruitlist array
        searchController = UISearchController(searchResultsController: resultsController)
        //Initialize search controller with the resultsController when it has search results to display
        
        //Search bar configuration
        searchController.searchBar.placeholder = "Enter a fruit" //place hoder text
        searchController.searchBar.sizeToFit() //sets size for search bar
        tableView.tableHeaderView=searchController.searchBar //install the search bar as table header
        searchController.searchResultsUpdater = resultsController //sets the instance to update results

    }
    

    //Required methods for UITableViewDataSource
    //Customize the number of rows in the section
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return fruitlist.fruits.count
    }
    
    //Display table view cells
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        //dequeue an existing cell if one is available, or creates a new one and adds it to the table
        let cell = tableView.dequeueReusableCell(withIdentifier: "CellIdentifier", for:indexPath)
        cell.textLabel?.text = fruitlist.fruits[indexPath.row]
        cell.imageView?.image = UIImage(named:self.iconsArray[indexPath.row])
        return cell
    }

    
    //Tell DetailViewController which Fruit's varieties to show
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "fruitsegue" {
            let detailVC = segue.destination as! DetailViewController
            let indexPath = tableView.indexPath(for: sender as! UITableViewCell)!
            
            //set data for the destination controller
            detailVC.title = fruitlist.fruits[indexPath.row]
            detailVC.fruitlistDetail = fruitlist
            detailVC.selectedFruit = indexPath.row
        }
    }
    
    //called when the UIApplicationWillResignActiveNotification notification is posted
    //all notification methods take a single NSNotification instance as their argument
    @objc func applicationWillResignActive(_ notification: NSNotification){
        //get path for data file
        let dirPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        let docDir = dirPath[0] //documents directory
        print(docDir)
        
        // URL for our plist
        let dataFileURL = docDir.appendingPathComponent(kfilename)
        print(dataFileURL)
        //creates a property list decoder object
        let plistencoder = PropertyListEncoder()
        plistencoder.outputFormat = .xml
        do {
            let data = try plistencoder.encode(fruitlist.fruitData)
            try data.write(to: dataFileURL)
        } catch {
            // handle error
            print(error)
        }
    }
    
    
    
    //Make TableView Cell transparent
    override func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        cell.backgroundColor = .clear
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

