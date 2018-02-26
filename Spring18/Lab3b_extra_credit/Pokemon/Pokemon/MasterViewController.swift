//
//  MasterViewController.swift
//  Pokemon
//
//  Created by Keke Wu on 2/25/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit

protocol MonsterSelectionDelegate: class {
    
    func monsterSelected(_ newMonster: Monster)
}

class MasterViewController: UITableViewController {
    
    weak var delegate:MonsterSelectionDelegate?
    
    let monsters = [
        Monster(name: "Bulbasaur", description: "Grass Poison",
                iconName: "bulbasaur"),
        Monster(name: "Butterfree", description: "Bug Flying",
                iconName: "butterfree"),
        Monster(name: "Caterpie", description: "Bug",
                iconName: "caterpie"),
        Monster(name: "Charmander", description: "Fire",
                iconName: "charmander"),
        Monster(name: "Goldeen", description: "Water",
                iconName: "goldeen"),
        Monster(name: "Jigglypuff", description: "Normal Fairy",
                iconName: "jigglypuff"),
        Monster(name: "Pikachu", description: "Electric",
                iconName: "pikachu"),
        Monster(name: "Psyduck", description: "Water",
                iconName: "psyduck"),
        Monster(name: "Squirtle", description: "Water",
                iconName: "squirtle"),
        Monster(name: "Vulpix", description:"Fire",
                iconName:"vulpix")
    ]

    
    // MARK: - Table view data source

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return monsters.count
    }

        override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
            let selectedMonster = monsters[indexPath.row]
            delegate?.monsterSelected(selectedMonster)
            if let detailViewController = delegate as? DetailViewController,
                let detailNavigationController = detailViewController.navigationController {
                splitViewController?.showDetailViewController(detailNavigationController, sender: nil)
            }
        }
 
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        let monster = monsters[indexPath.row]
        cell.textLabel?.text = monster.name
        return cell
    }
}

