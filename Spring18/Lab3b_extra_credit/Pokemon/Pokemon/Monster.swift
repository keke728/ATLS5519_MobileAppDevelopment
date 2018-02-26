//
//  Monster.swift
//  Pokemon
//
//  Created by Keke Wu on 2/25/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import UIKit



class Monster {
    let name: String
    let description: String
    let iconName: String

    
    init(name: String, description: String, iconName: String) {
        self.name = name
        self.description = description
        self.iconName = iconName
    }
    
    
    var icon: UIImage? {
        return UIImage(named: iconName)
    }
}
