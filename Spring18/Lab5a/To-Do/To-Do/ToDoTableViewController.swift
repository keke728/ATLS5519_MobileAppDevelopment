//
//  ToDoTableViewController.swift
//  To-Do
//
//  Created by Keke Wu on 3/10/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit
import Firebase

class ToDoTableViewController: UITableViewController {
    
    var ref:DatabaseReference!
    var todos = [ToDoList]()
    
    @IBAction func unwindSegue(segue:UIStoryboardSegue){
        if segue.identifier == "saveSegue" {
            let source = segue.source as! AddViewController
            if source.addedToDo.isEmpty == false{
                let newToDo = ToDoList(name: source.addedToDo, url: source.addedURL)
                todos.append(newToDo)
                let newToDoDict = ["name": source.addedToDo, "url": source.addedURL]
                let todoref = ref.child(source.addedToDo)
                todoref.setValue(newToDoDict)
            }
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showDetail" {
            let detailVC = segue.destination as! WebViewController
            let indexPath = tableView.indexPath(for: sender as! UITableViewCell)!
            let todo = todos[indexPath.row]
            detailVC.title = todo.name
            detailVC.webpage = todo.url
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        ref = Database.database().reference()
        
        //Set up a listener for Firebase data change events
        ref.observe(DataEventType.value, with: {snapshot in self.todos=[]
            for todo in snapshot.children.allObjects as! [DataSnapshot]{
                if let todoValue = todo.value as? [String: String],
                    let json = try? JSONEncoder().encode(todoValue),
                    let newToDo = try? JSONDecoder().decode(ToDoList.self, from: json)
                {
                    self.todos.append(newToDo)
                }
            }
            self.tableView.reloadData()
        })
        

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
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
        return todos.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "todocell", for: indexPath)
        let todo = todos[indexPath.row]
        cell.textLabel!.text = todo.name
        return cell
    }
    


    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }
  


    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            let todo = todos[indexPath.row]
            let todoref = ref.child(todo.name)
            // Delete the row from Firebase
            todoref.ref.removeValue()
        }
    }


    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
