//
//  DetailViewController.swift
//  Fruit
//
//  Created by Keke Wu on 2/17/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit

class DetailViewController: UITableViewController {
    
    var variety = [String]()
    var selectedFruit = 0
    var fruitlistDetail = Fruits()
    
    @IBAction func unwindSegue (_ segue:UIStoryboardSegue){
        if segue.identifier == "doneSegue"{
            let source = segue.source as! AddVarietyViewController
            //only add a variety if there is text in the textfield
            if((source.addedVariety.isEmpty) == false){
                //add variety to the array
                variety.append(source.addedVariety)
                tableView.reloadData()
                let chosenFruit = fruitlistDetail.fruits[selectedFruit]
                //add variety to our data model instance
                fruitlistDetail.fruitData[chosenFruit]?.append(source.addedVariety)
            }
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        fruitlistDetail.fruits = Array(fruitlistDetail.fruitData.keys).sorted() //Sort by letter
        let chosenFruit = fruitlistDetail.fruits[selectedFruit]
        variety = fruitlistDetail.fruitData[chosenFruit]! as [String]
        
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //Set BG image
        tableView.backgroundView = UIImageView(image: UIImage(named:"bg1")!)
        
        // Display an Edit button in the navigation bar for this view controller.
        //self.navigationItem.rightBarButtonItem = self.editButtonItem
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Table view data source
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return variety.count
    }
    
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "CellIdentifier", for: indexPath)
        
        // Configure the cell...
        cell.textLabel?.text = variety[indexPath.row]
        cell.textLabel?.font = UIFont(name:"Kefa", size:18)
        return cell
    }
    
    
    
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    
    
    
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the variety from the array
            variety.remove(at: indexPath.row)
            let chosenFruit = fruitlistDetail.fruits[selectedFruit]
            
            // Delete from the data model instance
            fruitlistDetail.fruitData[chosenFruit]?.remove(at: indexPath.row)
            
            //Delete the row from the table
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }
    }
    
    
    
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to toIndexPath: IndexPath) {
        let fromRow = fromIndexPath.row // row being moved from
        let toRow = toIndexPath.row //row being moved to
        let moveVariety = variety[fromRow] //variety being moved
        //move in array
        variety.remove(at: fromRow)
        variety.insert(moveVariety, at: toRow)
        //move in data model instance
        let chosenFruit = fruitlistDetail.fruits[selectedFruit]
        fruitlistDetail.fruitData[chosenFruit]?.remove(at: fromRow)
        
        fruitlistDetail.fruitData[chosenFruit]?.insert(moveVariety, at: toRow)
    }
    
    //Make TableView Cell transparent
    override func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        cell.backgroundColor = .clear
    }
    
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not" want the item to be re-orderable.
        return true
    }
    
    
    
}

