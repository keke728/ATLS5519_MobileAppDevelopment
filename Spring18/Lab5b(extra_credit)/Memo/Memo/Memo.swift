//
//  Memo.swift
//  Memo
//
//  Created by Keke Wu on 3/15/18.
//  Copyright © 2018 Keke Wu. All rights reserved.
//

import Foundation
import RealmSwift

class Memo: Object {
    @objc dynamic var name = ""
    @objc dynamic var done = false
}
