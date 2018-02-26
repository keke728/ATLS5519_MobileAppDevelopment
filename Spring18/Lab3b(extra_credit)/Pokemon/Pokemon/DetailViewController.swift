//
//  DetailViewController.swift
//  Pokemon
//
//  Created by Keke Wu on 2/25/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {

    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var imgView: UIImageView!
    @IBOutlet weak var descriptionLabel: UILabel!
    
    var monster: Monster?{
        didSet {
            refreshUI()
        }
    }
    
    //Helper method
    func refreshUI(){
      loadViewIfNeeded()
        nameLabel.text = monster?.name
        descriptionLabel.text = monster?.description
        imgView.image = monster?.icon
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}

extension DetailViewController: MonsterSelectionDelegate {
    func monsterSelected(_ newMonster: Monster) {
        monster = newMonster
    }
}
